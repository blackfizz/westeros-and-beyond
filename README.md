# westeros-and-beyond
## TODO
* Fix bug in HousePagingSource which results in loading all house pages when we start to scroll on 
the house list and the view jumps until all data is loaded. We can see it as a feature, as the 
database is now fully populated and the paging works perfectly 
* Add Unit Test for Remote/Local and HouseRemoteMediator
* Add Jetpack Compose Tests
* Add Refresh handling to invalidate cached data + use lastModified header
* Load characters from api with the provided urls in the e.g. overlord or heir members
* Add Toolbar
* Add sorting after region
* Add search
* Remember scroll position when going back from house detail to house list
* Test Kotlin Serialization Controller for retrofit (https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter)
* ... 