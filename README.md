# Weather App 🌤️

A modern Android weather application built with Kotlin and Jetpack Compose that provides real-time weather information for any city worldwide.

## 📱 Features

- Real-time weather data fetching
- Clean and intuitive Material Design UI
- Temperature display in Celsius
- Weather condition descriptions
- Location information display
- Built with modern Android development stack

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM
- **Networking**: Retrofit2 + Gson
- **Coroutines**: Kotlinx Coroutines
- **API**: WeatherAPI.com
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34

## 📋 Prerequisites

Before running this application, make sure you have:

- Android Studio (Latest version recommended)
- JDK 11 or higher
- An Android device or emulator with API level 24+
- WeatherAPI.com API key

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/Shivamtawar/weatherApk.git
cd weatherApk
```

### 2. Get API Key
1. Visit [WeatherAPI.com](https://www.weatherapi.com/)
2. Sign up for a free account
3. Navigate to your dashboard and copy your API key

### 3. Configure API Key
1. Open `app/src/main/java/com/example/watherapp/WeatherScreen.kt`
2. Replace `"Your_API_KEY"` with your actual API key:
```kotlin
val response = api.getCurrentWeather("YOUR_ACTUAL_API_KEY", city)
```

### 4. Build and Run
1. Open the project in Android Studio
2. Sync the project with Gradle files
3. Build and run the application on your device/emulator

## 📡 API Documentation

This app uses the [WeatherAPI.com](https://www.weatherapi.com/) Current Weather API.

### API Endpoint
```
GET https://api.weatherapi.com/v1/current.json
```

### Request Parameters
- `key`: Your API key (string, required)
- `q`: City name (string, required)

### Sample Request
```
https://api.weatherapi.com/v1/current.json?key=YOUR_API_KEY&q=London
```

### Response Structure

The API returns a JSON response with the following structure:

```json
{
  "location": {
    "name": "London",
    "country": "United Kingdom"
  },
  "current": {
    "temp_c": 15.0,
    "condition": {
      "text": "Partly cloudy",
      "icon": "//cdn.weatherapi.com/weather/64x64/day/116.png"
    }
  }
}
```

### Response Fields Explained

#### Location Object
| Field | Type | Description |
|-------|------|-------------|
| `name` | String | City name |
| `country` | String | Country name |

#### Current Object
| Field | Type | Description |
|-------|------|-------------|
| `temp_c` | Float | Temperature in Celsius |
| `condition` | Object | Weather condition details |

#### Condition Object
| Field | Type | Description |
|-------|------|-------------|
| `text` | String | Weather condition description |
| `icon` | String | Weather condition icon URL |

### Sample Response
```json
{
  "location": {
    "name": "Mumbai",
    "country": "India"
  },
  "current": {
    "temp_c": 28.5,
    "condition": {
      "text": "Sunny",
      "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png"
    }
  }
}
```

## 🏗️ Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/watherapp/
│   │   │   ├── MainActivity.kt          # Main activity
│   │   │   ├── WeatherApi.kt           # Retrofit API interface
│   │   │   ├── WeatherResponse.kt      # Data classes for API response
│   │   │   ├── WeatherScreen.kt        # Main UI composable
│   │   │   └── ui/theme/               # App theme and styling
│   │   └── AndroidManifest.xml
│   ├── androidTest/                    # Instrumented tests
│   └── test/                           # Unit tests
├── build.gradle.kts                    # App-level Gradle build file
└── proguard-rules.pro                  # ProGuard configuration
```

## 🎨 App Components

### MainActivity
- Entry point of the application
- Sets up the Compose content with the app theme

### WeatherApi
- Retrofit interface for API calls
- Defines the `getCurrentWeather` endpoint
- Creates and configures the Retrofit instance

### WeatherResponse
- Data classes representing the API response structure
- Includes `WeatherResponse`, `Location`, `Current`, and `Condition` classes

### WeatherScreen
- Main UI composable function
- Handles weather data fetching and display
- Shows loading states and error handling

## 🔧 Configuration

### Default Settings
- **Default City**: Nashik
- **Temperature Unit**: Celsius
- **API Base URL**: `https://api.weatherapi.com/v1/`

### Customization
You can modify the default city by changing the initial value in `WeatherScreen.kt`:
```kotlin
var city by remember { mutableStateOf("your_city_name") }
```

## 🚦 Error Handling

The app includes comprehensive error handling:
- Network connectivity issues
- Invalid API keys
- City not found
- API rate limiting
- General exceptions

Error messages are displayed to the user with helpful information.

## 🧪 Testing

### Running Tests
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

### Test Structure
- **Unit Tests**: Located in `app/src/test/`
- **Instrumented Tests**: Located in `app/src/androidTest/`

## 📱 App Permissions

This app requires the following permission:
- `INTERNET` - To fetch weather data from the API

## 🔄 API Rate Limits

WeatherAPI.com free tier includes:
- 1,000,000 calls per month
- No rate limiting on requests
- Real-time weather data

## 🐛 Troubleshooting

### Common Issues

1. **App crashes on startup**
   - Ensure you have added your API key
   - Check internet connectivity

2. **No weather data displayed**
   - Verify your API key is valid
   - Check if the city name is spelled correctly

3. **Build errors**
   - Clean and rebuild the project
   - Sync Gradle files

### Debug Tips
- Check Logcat for detailed error messages
- Ensure your device/emulator has internet access
- Verify API key permissions on WeatherAPI.com dashboard

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

### Development Setup
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

If you encounter any issues or have questions:
1. Check the [Issues](https://github.com/Shivamtawar/weatherApk/issues) section
2. Create a new issue with detailed information
3. Contact the maintainer: [@Shivamtawar](https://github.com/Shivamtawar)

## 🔮 Future Enhancements

- [ ] 7-day weather forecast
- [ ] Weather maps integration
- [ ] Location-based weather detection
- [ ] Weather alerts and notifications
- [ ] Multiple cities support
- [ ] Weather history and trends
- [ ] Dark/Light theme toggle
- [ ] Weather widgets

---

**Made with ❤️ by [Shivam Tawar](https://github.com/Shivamtawar)**
