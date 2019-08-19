import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

/**
 * @author kaibo
 * @date 2019/8/15 15:06
 * @GitHub：https://github.com/yuxuelian
 * @email：kaibo1hao@gmail.com
 * @description：
 */
class Message : Delayed {
    var what = 0
    var target: Handler? = null
    var `when` = 0L
    var obj: Any? = null
    var callback: Runnable? = null

    override fun compareTo(other: Delayed) = if (other is Message) {
        (`when` - other.`when`).toInt()
    } else {
        (getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS)).toInt()
    }

    override fun getDelay(unit: TimeUnit): Long {
        return unit.convert(`when` - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
    }
}
