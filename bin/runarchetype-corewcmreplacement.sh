#!/bin/bash
echo Creates a project from this archetype via script, so that variations of the arguments can be easily tested.
echo Copy this script and edit as needed.

# we use org.apache.maven.plugins:maven-archetype-plugin:3.1.0:generate since strangely the 3.1.2 creates too many newlines in the poms for some reason

tenant="composum"
domain="prototype"
project="aem-wcm-core-replacement"
groupId="com.composum.prototype.aemwcmcorereplacement"
artifactId="composum-prototype-aemwcmcorereplacement"
version="1.0.0-SNAPSHOT"
package="composum.prototype.aemwcmcorereplacement"
projectname="Partial AEM Core Replacement"
projectdescription="Explorative partial replacement for the aem core wcm components in a migration from AEM to Composum Pages"

mvn "org.apache.maven.plugins:maven-archetype-plugin:3.1.0:generate" -DinteractiveMode=false -DarchetypeGroupId=com.composum.meta.ist.archetype \
    -DarchetypeArtifactId=composum-pages-project-archetype -DarchetypeVersion="1.0-SNAPSHOT" -DgroupId="$groupId" -DartifactId="$artifactId" -Dpackage="$package" \
    -Dtenant="$tenant" -Ddomain="$domain" -Dproject="$project" -Dpackage="$package" -Dprojectname="$projectname" -Dprojectdescription="$projectdescription" \
    -Dgoals="clean install"
