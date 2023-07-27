import logging
import yaml

from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base

logger = logging.getLogger(__name__)

with open('rewards/config.yaml', 'rt') as f:
    config = yaml.safe_load(f.read())
logger.info("Connecting to: %s" % config['db']['connect_url'])
engine = create_engine(config['db']['connect_url'])
db_session = scoped_session(sessionmaker(autocommit=False,
                                         autoflush=False,
                                         bind=engine))
Base = declarative_base()
Base.query = db_session.query_property()


def init_db():
    # import all modules here that might define models so that
    # they will be registered properly on the metadata.  Otherwise
    # you will have to import them first before calling init_db()
    import rewards.models
    logger.info("Initializing Rewards DB...")
    Base.metadata.create_all(engine)
    logger.info("Base metadata: %s" % str(Base.metadata))
