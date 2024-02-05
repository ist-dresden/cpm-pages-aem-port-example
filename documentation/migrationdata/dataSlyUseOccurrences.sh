#!/usr/bin/env bash
# Find all occurrences of data-sly-use in the project and output the class name to file mapping
# data-sly-use.textModel="com.adobe.cq.wcm.core.components.models.Text"
# data-sly-use.component="com.adobe.cq.wcm.core.components.models.Component"
# data-sly-use.templates="core/wcm/components/commons/v1/templates.html"
# Occurrence in foo/bar/baz.html: output e.g.
# com.adobe.cq.wcm.core.components.models.Text foo/bar/baz.html

# ggrep is GNU grep, which is available on Mac OS X via brew install grep

dir=composum-prototype-aemwcmcorereplacement/app/package/src/main/content/jcr_root/apps/core/wcm/components
(
for file in $(find $dir -name "*.html"); do
    ggrep -oP 'data-sly-use\.[^=]*="\K[^"]*' $file | while read -r line; do
        # output file relative to dir
        echo "$line $(dirname ${file#$dir/})"
    done
done
) | sort -u
