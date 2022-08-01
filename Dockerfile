FROM clojure

WORKDIR /usr/src/app

COPY project.clj ./
RUN lein deps

COPY src ./src
RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" app-standalone.jar

CMD ["java", "-jar", "app-standalone.jar"]