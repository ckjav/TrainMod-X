package com.x.trainmodx

import platform.Foundation.date
import platform.Foundation.timeIntervalSince1970

class TimeUtils {
}
actual fun currentTimeMillis(): Long =
    (platform.Foundation.NSDate.date().timeIntervalSince1970 * 1000).toLong()