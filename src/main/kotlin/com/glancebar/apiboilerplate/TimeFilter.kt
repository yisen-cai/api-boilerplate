package com.glancebar.apiboilerplate

import com.glancebar.apiboilerplate.utils.Log
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse


/**
 *
 * @author Ethan Gary
 * @date 2020/12/16
 */
class TimeFilter : Filter {

    companion object : Log()

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        logger.info("time filter init....")
        var start = System.currentTimeMillis()
        chain!!.doFilter(request, response)
        val end = System.currentTimeMillis()
        logger.info("time filter ended....")
        logger.info("time filter last: " + (end - start) + " milliseconds")
    }
}