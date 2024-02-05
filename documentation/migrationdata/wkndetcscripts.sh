# Some scripts used to produce these files

srcdir=/Users/hps/dev/composum/examples/aem-core-wcm-components/content/src/content/jcr_root/apps
dstdir=/Users/hps/dev/composum/cpm-pages-aem-port-example/composum-prototype-aemwcmcorereplacement/app/package/src/main/content/jcr_root/apps
supertypesfil=/Users/hps/dev/composum/cpm-pages-aem-port-example/documentation/wkndresourcesupertypes.txt

## wkndresourcesupertypes.txt:
# find aem-guides-wknd/ui.apps/src/main/content/jcr_root/apps/wknd/components -name '.content.xml' -exec grep -oP 'sling:resourceSuperType="\K[^"]*' {} \; | sort -u

## copy the directories in the $supertypesfil from srcdir to destdir

if true; then

    for component in $(cat $supertypesfil); do
        # echo "copying $srcdir/$component to $dstdir/$component"
        # if it contains /v1/ like core/wcm/components/embed/v1/embed or /v2/ like core/wcm/components/form/v2/form then copy the parent of the parent.
        if [[ $component == */v1/* ]]; then
            component=$(dirname $(dirname $component))
        elif [[ $component == */v2/* ]]; then
            component=$(dirname $(dirname $component))
        fi
        # rm -fr $dstdir/$component
        mkdir -p $dstdir/$component
        cp -r $srcdir/$component $(dirname $dstdir/$component)
    done

    ## remove unused directories with higher version

    for component in $(cat $supertypesfil); do
        # if it contains /v1/ like core/wcm/components/embed/v1/embed or /v2/ like core/wcm/components/form/v2/form then
        # remove the directories with higher version up to v3: /v2/ and /v3/ if /v1/ is used, /v3/ if /v2/ is used, etc.
        if [[ $component == */v1/* ]]; then
            component=$(dirname $component)
            component=$(dirname $component)
            echo "removing $dstdir/$component/v2 and $dstdir/$component/v3"
            rm -fr $dstdir/$component/v2
            rm -fr $dstdir/$component/v3
        elif [[ $component == */v2/* ]]; then
            component=$(dirname $component)
            component=$(dirname $component)
            rm -fr $dstdir/$component/v3
        fi
    done

fi

## remove empty directories in $dstdir

find $dstdir -type d -empty -delete
