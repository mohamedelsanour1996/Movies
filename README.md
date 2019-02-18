# Movies (Udcity PopularMovies Stage 2)
# Feature
   - Show Movies by Popular and High Rated
   - Show Movies Description
   - Watch Trailer
   - Show Reviews
   ## PROJECT SPECIFICATION

### User Interface - Layout
-UI contains an element (e.g., a spinner or settings menu) to toggle the sort order of the movies by: most popular, highest rated.

-Movies are displayed in the main layout via a grid of their corresponding movie poster thumbnails.

-UI contains a screen for displaying the details for a selected movie.

-Movie Details layout contains title, release date, movie poster, vote average, and plot synopsis.

-Movie Details layout contains a section for displaying trailer videos and user reviews.

### User Interface - Function
-When a user changes the sort criteria (most popular, highest rated, and favorites) the main view gets updated correctly.

-When a movie poster thumbnail is selected, the movie details screen is launched.

-When a trailer is selected, app uses an Intent to launch the trailer.

-In the movies detail screen, a user can tap a button(for example, a star) to mark it as a Favorite.

### Network API Implementation
-In a background thread, app queries the `/movie/popular` or `/movie/top_rated` API for the sort criteria specified in the settings menu.

-App requests for related videos for a selected movie via the `/movie/{id}/videos` endpoint in a background thread and displays those details when the user selects a movie.

-App requests for user reviews for a selected movie via the `/movie/{id}/reviews` endpoint in a background thread and displays those details when the user selects a movie.

### Things I've Learnt :
* Working with [Movies API](https://www.themoviedb.org/?language=en)
* [Retrofit 2.5.0](https://square.github.io/retrofit/)
* [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview#structure)
* JSON parsing
* Scrolling view
* Picasso
* Bottom Navigation Bar
* Grid View
* Play videos in YouTube using implicit intents
* Favorite movies tracking
* [Room](https://developer.android.com/training/data-storage/room/)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* Working with URIs
