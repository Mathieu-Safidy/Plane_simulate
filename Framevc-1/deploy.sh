#!/bin/bash

projet="framevc-1"
projet_mere="Framevc-1"
librairies="lib"
librairies_temp="$projet/lib"
classFile="src/class"
java_dir="src/org/springcopy"
test_dir="../../TICKETING/ticket/lib"  # Adapté pour WSL, sinon change
page="src/org/page"

# Compilation Java
javac -parameters -cp "$librairies/*:$librairies_temp" -d "$projet" \
  "$java_dir/core/"*.java \
  "$java_dir/annote/"*.java \
  "$java_dir/exception/"*.java

# Copier les pages
mkdir -p "$projet/org/page"
cp -r "$page/"* "$projet/org/page/"

# Créer le JAR
cd "$projet" || exit 1
jar -cvf "../$projet.jar" org/*

# Copier le JAR vers le dossier de test
cp "../$projet.jar" "$test_dir/$projet.jar"

echo "✅ Build terminé."
