# stockwatchlist
# Stock Watchlist App

## Overview

The **Stock Watchlist** app is an Android application built using Kotlin. It allows users to search for stocks, view live stock prices, and maintain a personal watchlist of selected stocks. The app integrates with the **Alphavantage API** to fetch stock data and uses local storage to save the user's watchlist.

---

## Features

### Home Screen
- **Search Bar**: Allows users to type a company's name or stock symbol.
- **Autocomplete Search**: Displays relevant search results as the user types, showing company names and stock prices.
- **API Integration**: Fetches data using the Alphavantage API.
- **Add to Watchlist**: Users can tap the "+" button to save a stock to their watchlist.

### Watchlist Screen
- **List Display**: Displays all saved stocks with their most recent prices.
- **Delete Option**: Users can remove stocks from the watchlist.

---

## Technical Specifications

### Programming Language
- **Kotlin**

### UI Components
- **RecyclerView**: For displaying lists of stocks on both screens.
- **Toolbar**: Includes a title bar indicating the current screen.
- **SearchView**: Enables search functionality.

### Networking
- **Retrofit**: Used to make API calls to the Alphavantage API.

### Local Storage
- **Room Database**: Manages saving and retrieving watchlist data.

### Error Handling
- Displays a friendly message if:
  - API requests fail.
  - No search results are found.

### Bonus Features
- **LiveData or Flow**: Observes changes in the Room database, ensuring real-time updates to the UI.
- **Coroutines**: Handles background tasks smoothly.
- **Unit Tests**: Basic tests for components like the data repository and API service.

---

## Getting Started

### Prerequisites
- **Android Studio** (latest version recommended)
- A valid **Alphavantage API key**

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/stock-watchlist.git
   ```
2. Open the project in Android Studio.
3. Add your Alphavantage API key in the `gradle.properties` file:
   ```properties
   ALPHA_VANTAGE_API_KEY="your_api_key_here"
   ```

4. Build and run the project on an emulator or device.

---

## Usage

1. **Home Screen**: Use the search bar to look for a company or stock symbol. Tap the "+" button to add a stock to your watchlist.
2. **Watchlist Screen**: View your saved stocks. Remove stocks by tapping the delete icon.

---

## Project Structure

```plaintext
src/
├── data/
│   ├── model/          # Data models for stock information
│   ├── repository/     # Handles data operations (API and Room)
│   └── database/       # Room database and DAO classes
├── network/
│   ├── api/            # Retrofit API interface
│   └── service/        # Retrofit service configuration
├── ui/
│   ├── home/           # Home screen components
│   ├── watchlist/      # Watchlist screen components
│   └── adapters/       # RecyclerView adapters
├── utils/              # Utility classes (e.g., error handling, extensions)
└── MainActivity.kt     # Entry point for the app
```

---

## Dependencies

Add the following dependencies in your `build.gradle` file:

```gradle
dependencies {
    // Retrofit for network calls
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // Room for local database
    implementation 'androidx.room:room-runtime:2.5.0'
    kapt 'androidx.room:room-compiler:2.5.0'

    // Coroutines for background tasks
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0'

    // LiveData and ViewModel
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0'

    // RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.3.0'

    // Material Design
    implementation 'com.google.android.material:material:1.10.0'
}
```

---

## API Integration

### Alphavantage API
- Base URL: `https://www.alphavantage.co/`
- Example Endpoint:
  ```plaintext
  https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=IBM&apikey=your_api_key
  ```
- Response includes company name, symbol, and other details.

### Retrofit Configuration
Define the Retrofit API service:

```kotlin
interface AlphaVantageApi {
    @GET("query")
    suspend fun searchStocks(
        @Query("function") function: String = "SYMBOL_SEARCH",
        @Query("keywords") keywords: String,
        @Query("apikey") apiKey: String
    ): Response<StockSearchResponse>
}
```

---

## Future Improvements
- Add stock charts and detailed statistics.
- Enable notifications for stock price updates.
- Allow customization of the watchlist (e.g., reorder items).

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.
