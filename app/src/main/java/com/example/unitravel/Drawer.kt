package com.example.unitravel

import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat

// Função para configurar o DrawerLayout
fun setupDrawer(drawerLayout: DrawerLayout, menuIcon: ImageView) {
    menuIcon.setOnClickListener {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}