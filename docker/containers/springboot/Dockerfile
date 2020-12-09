FROM maven:3.6.3-jdk-11

COPY startup.sh /usr/local/bin/startup.sh
RUN apt-get update && apt-get install dos2unix && dos2unix /usr/local/bin/startup.sh && chmod +x /usr/local/bin/startup.sh

#Start application
WORKDIR /usr/src/mymaven
ENTRYPOINT ["/usr/local/bin/startup.sh"]
CMD ["bash"]