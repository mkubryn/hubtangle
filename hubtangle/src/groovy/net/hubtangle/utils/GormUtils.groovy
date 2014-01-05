package net.hubtangle.utils

import net.hubtangle.api.command.SearchCommand

/**
 * Helper for GORM. Must be called inside gorm context!
 */
class GormUtils {
    static <T> Collection<T> fetchAllByIds(Class<T> type, List<Long> ids,
                                           SearchCommand searchCommand) {

    }
}
