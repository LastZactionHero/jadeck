FROM gitpod/workspace-full

USER gitpod

# Apply user-specific settings
WORKDIR /tmp
RUN wget https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-268.0.0-linux-x86_64.tar.gz
RUN tar -xvf google-cloud-sdk-268.0.0-linux-x86_64.tar.gz
RUN /tmp/google-cloud-sdk/install.sh --quiet

# Give back control
USER root
