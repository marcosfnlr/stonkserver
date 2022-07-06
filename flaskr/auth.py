from hashlib import sha256
from flask import (
    Blueprint, request
)

from flaskr.user import User

bp = Blueprint('auth', __name__, url_prefix='/auth')


@bp.route('/login', methods=(['POST']))
def login():
    email = request.json.get('email', '')
    password = request.json.get('password', '')
    if valid_login(email, password):
        user = User(email if '@' in email else email+'@email.com.br')
        return user.to_json()

    return {'message': 'Failed to login!'}, 401


def valid_login(email, password):
    userPass = get_user_pass(email)

    if userPass != None:
        h = sha256()
        h.update(password.encode('utf-8'))
        hash = h.hexdigest()
        if hash == userPass:
            return True

    return False


def get_user_pass(email):
    if email != 'a':
        return 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb'
    return None
