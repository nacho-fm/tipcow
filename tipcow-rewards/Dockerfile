# TipCow Rewards Server
#
# VERSION               0.0.1
FROM ubuntu:latest
LABEL maintainer="chris@tipcow.me"

RUN mkdir -p /opt/tipcow/logs
RUN touch /opt/tipcow/logs/rewards.log

RUN apt-get update -y
RUN apt-get install -y python3-pip

COPY . /tipcow
WORKDIR /tipcow

RUN pip3 install --upgrade pip
RUN pip3 install -r requirements.txt

ENV PYTHONPATH $PYTHONPATH:/tipcow

EXPOSE 5000

ENTRYPOINT ["python3"]
CMD ["rewards/app.py"]
