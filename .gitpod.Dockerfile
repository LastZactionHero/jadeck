FROM gitpod/workspace-full

USER gitpod

# Apply user-specific settings
WORKDIR /home/gitpod
RUN wget https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-268.0.0-linux-x86_64.tar.gz
RUN tar -xvf google-cloud-sdk-268.0.0-linux-x86_64.tar.gz
RUN google-cloud-sdk/install.sh --quiet --path-update true
RUN rm google-cloud-sdk-268.0.0-linux-x86_64.tar.gz

RUN gcloud components install app-engine-java --quiet

# Give back control
USER root
