from flask import request, jsonify
from rewards.app import app
from rewards.db import db_session
from rewards.models import Checkin

logger = logging.getLogger(__name__)

BASE_ROUTE = "/api/rewards"


@app.route("/")
def root_route():
    return jsonify({'Howdy': 'internet'})


def _rollback(err_msg):
    logger.error("Rolling back...")
    logger.error("Error: %s" % err_msg)
    db_session.rollback()


def _get_checkins():
    resp = jsonify({})
    try:
        resp = jsonify([q.as_dict() for q in Checkin.query.all()])
    except Exception as e:
        _rollback(str(e))
    finally:
        return resp


def _post_checkins(request):
    input_json = request.get_json()
    input_phone = input_json["phone"]
    input_firstname = input_json["firstname"]
    checkin = Checkin(phone=input_phone, firstname=input_firstname)

    try:
        db_session.add(checkin)
        db_session.commit()
    except Exception as e:
        _rollback(str(e))
    return jsonify(checkin.as_dict())


@app.route(BASE_ROUTE + "/checkins", methods=['GET', 'POST'])
def checkins():
    if request.method == 'POST':
        return _post_checkins(request)
    else:
        return _get_checkins()
