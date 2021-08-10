#!/bin/bash
# Assumes $PWD is your home directory with .git-credentials
# Need to use export for script to inherit env vars.
# export target_ns=<your namespace>

cat<<EOF | kubectl -n ${target_ns} apply -f  -
kind: Secret
apiVersion: v1
metadata:
  name: github
type: Opaque
stringData:
  .gitconfig: |
    [user]
            email = ${target_ns}@ftc-llc.om
            name = ${target_ns}
    [credential]
            helper = store
  .git-credentials: |
    $(cat `pwd`/.git-credentials)
EOF