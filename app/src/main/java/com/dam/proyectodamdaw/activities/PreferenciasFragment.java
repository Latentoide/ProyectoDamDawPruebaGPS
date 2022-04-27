package com.dam.proyectodamdaw.activities;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.dam.proyectodamdaw.R;

import java.util.Arrays;
import java.util.List;

public class PreferenciasFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);

        ListPreference listPreference = findPreference("unidades");

        List<String> entries = Arrays.asList(getResources().getStringArray(R.array.unidades_entries));
        List<String> values = Arrays.asList(getResources().getStringArray(R.array.unidades_values));

        int val = values.indexOf(GestionPreferencias.getInstance().getUnidad(getContext()));

        listPreference.setSummary("Seleccionado: " + values.get(val));

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                String val = entries.get(values.indexOf(newValue));
                listPreference.setSummary("Seleccionado " + val);

                return false;
            }
        });

        ListPreference idiomaList = findPreference("idioma");

        List<String> idiomaEntry = Arrays.asList(getResources().getStringArray(R.array.idioma_entries));
        List<String> idiomaVal = Arrays.asList(getResources().getStringArray(R.array.idioma_values));


        int valId = idiomaVal.indexOf(GestionPreferencias.getInstance().getIdioma(getContext()));

        idiomaList.setSummary("Seleccionado: " + idiomaVal.get(valId));

        idiomaList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                String valId = idiomaEntry.get(idiomaVal.indexOf(newValue));
                idiomaList.setSummary("Seleccionado " + valId);

                return false;
            }
        });
    }
}
