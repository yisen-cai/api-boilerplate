package com.glancebar.apiboilerplate.vo

import javax.validation.constraints.NotBlank


/**
 *
 * @author Ethan Gary
 * @date 2020/12/16
 */
class AuthorityVO {

    @NotBlank(message = "name must be set")
    var name: String = ""

    var description: String = ""
}