PR = "r0"

SUMMARY = "A stream-oriented XML parser library"
DESCRIPTION = "Expat is an XML parser library written in C. It is a stream-oriented parser in which an application registers handlers for things the parser might find in the XML document (like start tags)"

# Override value of DEBIAN_GIT_BRANCH variable in debian-package class
DEBIAN_GIT_BRANCH = "stretch-master"

inherit debian-package
PV = "2.2.0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=9c3ee559c6f9dcee1043ead112139f4f"

inherit autotools gzipnative

# This package uses an archive format known to have issue with some
# versions of gzip
do_unpack[depends] += "gzip-native:do_populate_sysroot"

do_configure_prepend () {
        rm -f ${S}/conftools/libtool.m4
}

PACKAGES =+ "lib${DPN}"

FILES_lib${DPN} = "${libdir}/lib*${SOLIBS}"

DEBIANNAME_${PN}-dev = "lib${DPN}1-dev"

# expat-dev is equal to libexpat-dev
RPROVIDES_${PN}-dev += "lib${DPN}-dev"

BBCLASSEXTEND = "native nativesdk"
