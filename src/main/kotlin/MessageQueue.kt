import java.util.concurrent.DelayQueue

/**
 * @author kaibo
 * @date 2019/8/15 15:05
 * @GitHub：https://github.com/yuxuelian
 * @email：kaibo1hao@gmail.com
 * @description：
 */

class MessageQueue {

    private val delayQueue = DelayQueue<Message>()

    fun enqueueMessage(message: Message) {
        delayQueue.add(message)
    }

    fun next() = try {
        delayQueue.take()
    } catch (e: InterruptedException) {
        e.printStackTrace()
        null
    }

}
