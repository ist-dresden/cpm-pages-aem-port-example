<!-- AIGenVersion(1.0) -->

# Additional specification for com.adobe.cq.wcm.core.components.models.Image

The getLink() method should return the linkURL.
The getSrc() method should (nullsafe) return the fileReference plus "/jcr:content/renditions/original" .
