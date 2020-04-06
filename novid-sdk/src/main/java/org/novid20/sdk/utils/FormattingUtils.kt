/*
 *  Created by Christoph Kührer & Florian Knoll on 06.04.20 19:27
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 06.04.20 16:59
 */

package org.novid20.sdk.utils

import org.novid20.sdk.Logger

object FormattingUtils {

    private const val TAG = "FormattingUtils"

    fun normalizePhoneNumber(countryCode: Int, phone: String): String {
        Logger.verbose(
            TAG, "Calling normalizePhoneNumber with " +
                    "countryCode: $countryCode and " +
                    "phone: $phone"
        )

        // Remove all non numeric characters
        val numeric = phone.replace(Regex("[^0-9]"), "")

        // Removes all leading zeroes. e.g. from ->0<-6766475826 or ->00<-436769475645
        var noLeadingZeroes = numeric.replace(Regex("^0+"), "")

        if (noLeadingZeroes.startsWith(countryCode.toString(), true)) {
            // Remove leading country code,  eg. ->43<-660123456 to 66012345
            noLeadingZeroes = noLeadingZeroes.replaceFirst(countryCode.toString(), "", true)
        }

        return (countryCode.toString() + noLeadingZeroes).also {
            Logger.debug(TAG, "Normalized phone number: $it")
        }
    }
}