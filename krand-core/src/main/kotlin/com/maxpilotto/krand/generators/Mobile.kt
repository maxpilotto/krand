package com.maxpilotto.krand.generators

import com.maxpilotto.krand.processor.annotations.Generator

@Generator("android_id", String::class)
interface _AndroidIDGenerator

@Generator("apple_token", String::class)
interface _AppleTokenGenerator

@Generator("bb_pin", String::class)
interface _BlackBerryPINGenerator

@Generator("wp7_anid", String::class)
interface _WindowsPhone7ANIDGenerator

//@Generator("wp8_anid2", String::class)
//interface _WindowsPhone8ANID2Generator