#!/bin/bash

# === Début du script ===

# Active l'arrêt en cas d'erreur
set -e

# === Définition des variables ===
projet="Vol"
lib_path="lib"
path_class="src"
webapps="/opt/tomcat10/webapps"  # À adapter selon ton installation
xml_path="web/xml"
config_path="web/config"
file_temp="temp"
temp_lib="$file_temp/WEB-INF/lib"
temp_class="$file_temp/WEB-INF/classes"
temp_xml="$file_temp/WEB-INF"
page_web="web/page"
assets="web/assets"
config="src/config"
FILES_LIST="files.txt"

# === Copier les dépendances et ressources ===
cp -r "$lib_path" "$file_temp/WEB-INF/"         # lib vers WEB-INF/lib
cp -r "$xml_path" "$temp_xml"         # XML vers WEB-INF
cp -r "$page_web" "$file_temp"   # Pages web vers /page
cp -r "$assets" "$file_temp"   # Assets vers /assets

# === Copier les classes de config vers classes/config ===
# mkdir -p "$file_temp/WEB-INF/classes/config"
cp -r "$config" "$file_temp/WEB-INF/classes/"

# === Compilation désactivée ici dans les commentaires originaux ===
# cp "$temp_class" "$config_path"

# === Compilation des fichiers Java listés dynamiquement ===

# Supprimer le fichier files.txt s’il existe
rm -f "$FILES_LIST"

# Lister tous les fichiers .java dans PATH_CLASS et remplacer \ par \\
find "$path_class" -name "*.java" > "$FILES_LIST"

# === Construction du classpath (commenté comme dans ton script) ===
# CLASSPATH=""
# for j in "$lib_path"/*.jar; do
#     if [ -z "$CLASSPATH" ]; then
#         CLASSPATH="$j"
#     else
#         CLASSPATH="$CLASSPATH:$j"
#     fi
# done

# for j in "$temp_lib"/*.jar; do
#     CLASSPATH="$CLASSPATH:$j"
# done

# === Affichage du contenu des dossiers de libs (comme dir) ===
echo "📂 Contenu de $lib_path"
ls "$lib_path"

echo "📂 Contenu de $temp_lib"
ls "$temp_lib"

# === Compilation effective ===
javac -parameters -cp "$lib_path/*:$temp_lib/*" -d "$temp_class" @"$FILES_LIST"

# === Création du WAR ===
cd "$file_temp"
jar -cvf "$webapps/$projet.war" WEB-INF/* page/* assets/*
cd ..

# === Nettoyage désactivé (comme dans ton script original) ===
# rm -rf "$file_temp"

echo "✅ WAR généré : $webapps/$projet.war"
