package com.s0cl.util.animation



/**
 * @author yuxiangll
 * @since 2024/7/6 上午9:46
 * IntelliJ IDEA
 */
class Animation(var easing: Easing, var duration: Long) {
    var millis: Long = 0
    var startTime: Long

    var startValue: Double = 0.0
    var destinationValue: Double = 0.0
    var value: Double = 0.0
    var isFinished: Boolean = false

    init {
        this.startTime = System.currentTimeMillis()
    }

    /**
     * Updates the animation by using the easing function and time
     *
     * @param destinationValue the value that the animation is going to reach
     */
    fun run(destinationValue: Double) {
        this.millis = System.currentTimeMillis()
        if (this.destinationValue != destinationValue) {
            this.destinationValue = destinationValue
            this.reset()
        } else {
            this.isFinished = this.millis - this.duration > this.startTime
            if (this.isFinished) {
                this.value = destinationValue
                return
            }
        }

        val result = easing.function.apply(this.progress)
        if (this.value > destinationValue) {
            this.value = this.startValue - (this.startValue - destinationValue) * result
        } else {
            this.value = this.startValue + (destinationValue - this.startValue) * result
        }
    }

    val progress: Double
        /**
         * Returns the progress of the animation
         *
         * @return value between 0 and 1
         */
        get() = (System.currentTimeMillis() - this.startTime).toDouble() / duration.toDouble()

    /**
     * Resets the animation to the start value
     */
    fun reset() {
        this.startTime = System.currentTimeMillis()
        this.startValue = value
        this.isFinished = false
    }
}
