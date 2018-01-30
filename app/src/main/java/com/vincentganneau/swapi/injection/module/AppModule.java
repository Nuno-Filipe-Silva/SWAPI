package com.vincentganneau.swapi.injection.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.vincentganneau.swapi.model.api.SWApi;
import com.vincentganneau.swapi.model.dao.PlanetDao;
import com.vincentganneau.swapi.model.database.SWDatabase;
import com.vincentganneau.swapi.model.repository.PlanetRepository;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger {@link Module} that provides application scope instances.
 * @author Vincent Ganneau
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    /**
     * Provides the application {@link Context} instance as a singleton.
     * @param application the {@link Application} instance.
     * @return the application {@link Context} instance.
     */
    @Singleton
    @Provides
    Context provideContext(Application application) {
        return application;
    }

    /**
     * Provides the {@link SWApi} instance as a singleton.
     * @return the {@link SWApi} instance.
     */
    @Singleton
    @Provides
    SWApi provideSWApi() {
        return new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SWApi.class);
    }

    /**
     * Provides the {@link SWDatabase} instance as a singleton.
     * @param context the application {@link Context}.
     * @return the {@link SWDatabase} instance
     */
    @Singleton
    @Provides
    SWDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, SWDatabase.class,"swapi.db").build();
    }

    /**
     * Provides the {@link PlanetDao} instance as a singleton.
     * @param database the {@link SWDatabase} instance.
     * @return the {@link PlanetDao} instance.
     */
    @Singleton
    @Provides
    PlanetDao providePlanetDao(SWDatabase database) {
        return database.planetDao();
    }

    /**
     * Provides the {@link PlanetRepository} instance as a singleton.
     * @param api the {@link SWApi} instance.
     * @param planetDao the {@link PlanetDao} instance.
     * @return the {@link PlanetRepository} instance.
     */
    @Provides
    @Singleton
    public PlanetRepository providePlanetRepository(SWApi api, PlanetDao planetDao) {
        return new PlanetRepository(api, planetDao, Executors.newSingleThreadExecutor());
    }
}
