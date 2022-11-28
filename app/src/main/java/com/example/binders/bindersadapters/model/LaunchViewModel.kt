package com.example.binders.bindersadapters.model

data class LaunchViewModel(
        @JvmField val missionName: String?,
        @JvmField val missionDetails: String?,
        @JvmField val missionImageUrl: String?,
        @JvmField val flightNumber: Int ) {

        override fun equals(other: Any?): Boolean {
                if (other !is LaunchViewModel) return false
                //Here we check what equal means in order to make diffutils determinate if this object -> view needs update
                return  other.flightNumber == this.flightNumber
        }
}