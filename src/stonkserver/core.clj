(ns stonkserver.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer [POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [api-defaults]]
            [ring.middleware.json :refer [wrap-json-params]]
            [clojure.string :as str]
            [clojure.data.json :as json])
  (:import [com.google.common.hash Hashing]
           [java.nio.charset StandardCharsets])
  (:gen-class))

(defn- get-user-pass [email]
  (if (= email "a") false "ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb"))

(defn- validate-login [email pass]
  (if-let [userPass (get-user-pass email)]
    (let [passHash (.toString (.hashString (Hashing/sha256) pass StandardCharsets/UTF_8))]
      (= passHash userPass))
    false))

(defn- get-user-data [email]
  {:email (if (str/includes? email "@") email (str email "@email.com.br")),
   :name "Pablo Vittar"})

(defn- login [req]
  (let [email (get-in req [:params "email"])
        pass (get-in req [:params "password"])
        is-valid (validate-login email pass)]
    {:status  (if is-valid 200 401)
     :headers {"Content-Type" "application/json"}
     :body    (json/write-str
               (if is-valid
                 (get-user-data email)
                 {:message "Failed to login!"}))}))

(defroutes app-routes
  (POST "/login" [] login)
  (route/not-found "Error, page not found!"))


(defn -main
  "This is our main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (server/run-server (wrap-json-params app-routes api-defaults) {:port port})
    (println (str "Running on http:/127.0.0.1:" port))))
