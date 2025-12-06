package com.ttlabz.pombo.domain.exception

class SamePasswordException : RuntimeException(
    "The new password can't be equal the old one."
)