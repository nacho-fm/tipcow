from flask import jsonify
from sqlalchemy import Column, String
from rewards.db import Base


class Checkin(Base):
    __tablename__ = 'checkins'

    phone = Column(String, nullable=False, unique=True, primary_key=True)
    firstname = Column(String)

    def __init__(self, phone=None, firstname=None):
        self.phone = phone
        self.firstname = firstname

    def as_dict(self):
        return {c.name: getattr(self, c.name) for c in self.__table__.columns}

    def __repr__(self):
        return jsonify(self.as_dict())
