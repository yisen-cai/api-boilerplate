package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.vo.RoleVO
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


/**
 *
 * @author Ethan Gary
 * @date 2020/12/16
 */
@RestController
@RequestMapping("/roles")
class RoleController(

) {


    /**
     * get role
     */
    @PostMapping
    fun addRole(@RequestBody @Valid roleVO: RoleVO, bindingResult: BindingResult) {

    }

    /**
     * Retrieve all roles
     */
    @GetMapping
    fun getRoles() {

    }

    /**
     * Grant or Retrieve authorities
     */
    @PutMapping("/{roleId}")
    fun modifyRole(@PathVariable roleId: String) {

    }

    /**
     * Delete role by given roleId
     */
    @DeleteMapping("/{roleId}")
    fun deleteRole(@PathVariable roleId: String) {

    }
}