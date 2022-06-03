class User:

    def __init__(self, email, name='Pablo Vittar'):
        self.email = email
        self.name = name

    def to_json(self):
        return {
            "email": self.email,
            "name": self.name,
        }
