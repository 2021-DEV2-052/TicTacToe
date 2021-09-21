package be.kata.tictactoe

import android.app.Application
import be.kata.tictactoe.data.DefaultGameCreator
import be.kata.tictactoe.data.GameCreator
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TicTacToeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TicTacToeApp)
            modules(appModule)
        }
    }
}

val appModule = module {
    single<GameCreator> { DefaultGameCreator() }
}