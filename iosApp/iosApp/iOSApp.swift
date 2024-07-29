import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        NapierLoggingKt.setUpNapierLoggingDebug()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
