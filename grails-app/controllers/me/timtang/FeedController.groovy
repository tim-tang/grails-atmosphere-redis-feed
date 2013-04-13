package me.timtang

import me.timtang.MsgInfo;

/**
 * @author timtang
 *
 */
class FeedController {

    def index() {
        def results = MsgInfo.list()
        render(contentType: "text/json") {
            msgInfos= array {
              for (msgInfo in results) {
                 msg content: msgInfo.content
              }
            }
        }
    }
}
