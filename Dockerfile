FROM gitpod/workspace-full:latest

USER root
# Install custom tools, runtime, etc.
RUN apt-get update && apt-get install -y \
        ... \
    && apt-get clean && rm -rf /var/cache/apt/* && rm -rf /var/lib/apt/lists/* && rm -rf /tmp/*

USER gitpod

# Apply user-specific settings
WORKDIR /tmp
RUN wget https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-268.0.0-linux-x86_64.tar.gz
RUN tar -xzf google-cloud-sdk-268.0.0-linux-x86_64.tar.gz
WORKDIR /tmp/google-cloud-sdk-268.0.0-linux-x86_64
RUN ./install.sh

# Give back control
USER root