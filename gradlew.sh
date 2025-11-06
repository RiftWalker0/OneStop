#!/usr/bin/env sh
JAVA_HOME="${JAVA_HOME:-}"
if command -v ./gradlew >/dev/null 2>&1; then
  exec ./gradlew "$@"
fi
if [ -x "./gradlew" ]; then
  exec ./gradlew "$@"
fi
# Fallback when wrapper not bootstrapped: use system gradle if available
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi
echo "Please run with a Gradle wrapper-enabled environment or install Gradle 8.x"
exit 1
