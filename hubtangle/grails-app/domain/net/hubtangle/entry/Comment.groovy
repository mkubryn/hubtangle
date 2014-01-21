package net.hubtangle.entry

import net.hubtangle.api.search.Indexed
import net.hubtangle.user.HUser

class Comment implements Serializable {

    @Indexed
    String content

    @Indexed
    Date dateCreated

    static belongsTo = [author: HUser, entry: Entry]

    static constraints = {
        content size: 2..1000
    }

    static mapping = {
        dateCreated index: 'comment_date_created_idx'
        entry index: 'comment_entry_idx'
    }
}
