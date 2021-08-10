# Standalone Pod for Development (Containers on Steroids)
```bash
# Setup some env vars to make your life easier
# Namespace in which to run the pod
export target_ns=default

# You need to login to Nexus to create local credentials then we will copy them to a secret for the pod to pull its containers. See the Nexus admin for credentials.
docker login docker.ftc-llc.net

# Create the pull secret for the pod
kubectl create -n ${target_ns} secret generic regcred \
    --from-file=.dockerconfigjson=~/.docker/config.json \
    --type=kubernetes.io/dockerconfigjson

# Create the github credentials from your home directory
cd
./repos/dmos-backend-template/cluster/github-secret.sh

# Create the pod
cd ./repos/dmos/backend-template/cluster
kubectl apply -f backend-pod.yaml

# Run exec commands against pod

```