import logging
import logging.config
import yaml

from rewards.db import db_session, init_db

from flask import Flask

app = Flask(__name__)

logger = logging.getLogger(__name__)


@app.teardown_appcontext
def shutdown_session(exception=None):
    db_session.remove()


if __name__ == '__main__':
    with open('rewards/logging.yaml', 'rt') as f:
        config = yaml.safe_load(f.read())
    logging.config.dictConfig(config)
    logger = logging.getLogger("rewards")
    logger.info("Starting rewards app in DEBUG mode...")
    init_db()
    app.run(debug=True, host='0.0.0.0')
