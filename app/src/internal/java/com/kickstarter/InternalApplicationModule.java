package com.kickstarter;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.kickstarter.libs.ApiEndpoint;
import com.kickstarter.libs.BuildCheck;
import com.kickstarter.libs.InternalTools;
import com.kickstarter.libs.InternalToolsType;
import com.kickstarter.libs.preferences.StringPreference;
import com.kickstarter.libs.qualifiers.ApiEndpointPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModule.class)
public class InternalApplicationModule {
  @Provides
  @Singleton
  ApiEndpoint provideApiEndpoint(@ApiEndpointPreference @NonNull final StringPreference apiEndpointPreference) {
    return ApiEndpoint.from(apiEndpointPreference.get());
  }

  @Provides
  @Singleton
  @ApiEndpointPreference
  StringPreference provideApiEndpointPreference(@NonNull final SharedPreferences sharedPreferences) {
    return new StringPreference(sharedPreferences, "debug_api_endpoint", ApiEndpoint.PRODUCTION.url);
  }

  @Provides
  BuildCheck provideBuildCheck() {
    return BuildCheck.DEFAULT;
  }

  @Provides
  @Singleton
  @NonNull
  InternalToolsType providesInternalToolsType() {
    return new InternalTools();
  }
}
