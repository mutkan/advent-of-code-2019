package com.aoc.monitoring.moon

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MoonTest {

    @Test
    @DisplayName("Given a moon with no velocity, when constructing, then it should default velocity to (0,0,0)")
    fun defaultVelocity() {
        assertThat(Moon("Name", Point3D(1, 4, 5)).velocity).isEqualTo(Velocity3D(0,0,0))
    }

    @Test
    fun applyVelocity() {
        val moon = Moon("Ganymede", Point3D(1, 2, 3), Velocity3D(-2, 0, 3))
        moon.applyVelocity()
        assertThat(moon.position).isEqualTo(Point3D(-1, 2, 6))
    }

    @Test
    @DisplayName("Given two moons with different axes, when applying gravity between them, then it should affect the" +
    "velocity accordingly.")
    fun applyGravity() {
        val sourceMoon = Moon("Ganymede", Point3D(3, 2, 8), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(1, -4, 12), Velocity3D(0, 0, 0))
        sourceMoon.applyGravity(targetMoon)
        assertThat(sourceMoon.velocity).isEqualTo(Velocity3D(-1,-1,1))
        assertThat(targetMoon.velocity).isEqualTo(Velocity3D(1,1,-1))
    }

    @Test
    @DisplayName("Given two moons with the same axes, when applying gravity between them, then it should not affect the axes")
    fun applyGravitySameAxes() {
        val sourceMoon = Moon("Ganymede", Point3D(3, 2, 8), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 2, 8), Velocity3D(0, 0, 0))
        sourceMoon.applyGravity(targetMoon)
        assertThat(sourceMoon.velocity).isEqualTo(Velocity3D(0, 0, 0))
        assertThat(targetMoon.velocity).isEqualTo(Velocity3D(0, 0, 0))
    }

    @Test
    fun calculatePotentialEnergy() {
        val moon = Moon("Ganymede", Point3D(8, -12, -9), Velocity3D(-2, 0, 3))
        val kineticEnergy = moon.calculatePotentialEnergy()
        assertThat(kineticEnergy).isEqualTo(29)
    }

    @Test
    fun calculateKineticEnergy() {
        val moon = Moon("Ganymede", Point3D(-1, 2, 3), Velocity3D(-2, 0, 3))
        val potentialEnergy = moon.calculateKineticEnergy()
        assertThat(potentialEnergy).isEqualTo(5)
    }

    @Test
    fun calculateTotalEnergy() {
        val moon = Moon("Ganymede", Point3D(-1, 5, 3), Velocity3D(-2, 12, 3))
        val potentialEnergy = moon.calculateTotalEnergy()
        assertThat(potentialEnergy).isEqualTo(153)
    }

    @Test
    fun hasSamePositionAndVelocityX() {
        val sourceMoon = Moon("Ganymede", Point3D(3, 5, 1), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 2, 8), Velocity3D(0, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityX(targetMoon)).isTrue()
    }

    @Test
    fun doesNotHaveSamePositionX() {
        val sourceMoon = Moon("Ganymede", Point3D(1, 5, 1), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 2, 8), Velocity3D(0, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityX(targetMoon)).isFalse()
    }

    @Test
    fun doesNotHaveSameVelocityX() {
        val sourceMoon = Moon("Ganymede", Point3D(3, 5, 1), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 2, 8), Velocity3D(6, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityX(targetMoon)).isFalse()
    }

    @Test
    fun doesNotHaveSamePositionOrVelocityX() {
        val sourceMoon = Moon("Ganymede", Point3D(3, 5, 1), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(1, 2, 8), Velocity3D(6, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityX(targetMoon)).isFalse()
    }

    @Test
    fun hasSamePositionAndVelocityY() {
        val sourceMoon = Moon("Ganymede", Point3D(2, 5, 1), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 5, 8), Velocity3D(0, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityY(targetMoon)).isTrue()
    }

    @Test
    fun doesNotHaveSamePositionY() {
        val sourceMoon = Moon("Ganymede", Point3D(2, 5, 1), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 3, 8), Velocity3D(0, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityY(targetMoon)).isFalse()
    }

    @Test
    fun doesNotHaveSameVelocityY() {
        val sourceMoon = Moon("Ganymede", Point3D(2, 5, 1), Velocity3D(0, 5, 0))
        val targetMoon = Moon("Europa", Point3D(3, 5, 8), Velocity3D(0, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityY(targetMoon)).isFalse()
    }

    @Test
    fun doesNotHaveSamePositionOrVelocityY() {
        val sourceMoon = Moon("Ganymede", Point3D(2, 5, 1), Velocity3D(0, 5, 0))
        val targetMoon = Moon("Europa", Point3D(3, 3, 8), Velocity3D(0, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityY(targetMoon)).isFalse()
    }

    @Test
    fun hasSamePositionAndVelocityZ() {
        val sourceMoon = Moon("Ganymede", Point3D(2, 6, 8), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 5, 8), Velocity3D(0, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityZ(targetMoon)).isTrue()
    }

    @Test
    fun doesNotHaveSamePositionZ() {
        val sourceMoon = Moon("Ganymede", Point3D(2, 5, 1), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 3, 8), Velocity3D(0, 0, 0))
        assertThat(sourceMoon.hasSamePositionVelocityZ(targetMoon)).isFalse()
    }

    @Test
    fun doesNotHaveSameVelocityZ() {
        val sourceMoon = Moon("Ganymede", Point3D(2, 5, 8), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 3, 8), Velocity3D(0, 0, 8))
        assertThat(sourceMoon.hasSamePositionVelocityZ(targetMoon)).isFalse()
    }

    @Test
    fun doesNotHaveSamePositionOrVelocityZ() {
        val sourceMoon = Moon("Ganymede", Point3D(2, 5, 3), Velocity3D(0, 0, 0))
        val targetMoon = Moon("Europa", Point3D(3, 3, 8), Velocity3D(0, 0, 8))
        assertThat(sourceMoon.hasSamePositionVelocityZ(targetMoon)).isFalse()
    }

    @Test
    fun toStringTest() {
        val moon = Moon("Io", Point3D(3, 2, 8), Velocity3D(0, 0, 0))
        val toString = moon.toString()
        assertThat(toString).isEqualTo("pos=<x=3, y=2, z=8>, vel=<x=0, y=0, z=0>")
    }
}