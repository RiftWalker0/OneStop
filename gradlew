#!/usr/bin/env sh
# Minimal gradlew that delegates to 'gradle' if available, else downloads wrapper
DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
exec sh "$DIR/gradlew.sh" "$@"
