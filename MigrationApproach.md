# Approach to porting wcm.io parts

The whole wcm.io library is so large that it isn't possible to port it all to composum, especially since it's using
about 70 classes from AEM we'd have to emulate. The projects main focus is to show how it can be done, pave some way
for future ports of AEM projects to Composum Pages and maybe make some appropriate extensions of Composum Pages if
needed.

Thus, we take the WKND site as an example and migrate / port only those parts of the wcm.io components library that 
are actually used. We copy step by step the classes / components over to this project and modify them.

We base the port on commit 35b90febc3ecbe058ae77f0094b7018b3c34b303 (release 2.23.4).

## Markers

To clearly show which parts are copied from the wcm.io library, which parts are changed and which parts are new, we
will observe the following.

- Java classes that are unmodified or only slightly modified stay at the same packages. If we create new 
  implementations, we will use packages with com.composum in them.
  - Changes are marked with // CPMCHANGE
  - Commented out parts are marked with // CPMREMOVE
  - Additions are marked with // CPMADD
- Similarly, we try to copy components as they are
