# cat-book

App Android de **gatos** — catálogo paginado de imagens/raças consumido de uma API pública de gatos.

## Objetivo

Projeto de portfólio praticando arquitetura Android moderna: paginação, rede, imagens e ViewModel.

## Tecnologias

- Kotlin + Coroutines
- **Paging 3** (`paging-runtime`)
- **Retrofit 2** + Gson (consumo da cat API)
- **Coil** (carregamento de imagens)
- MVVM (`lifecycle-viewmodel-ktx`)
- Gradle 8.7 / compileSdk 35 (minSdk 21)

## Pré-requisitos

- **JDK 17**
- Android SDK Platform **34/35**

## Como rodar

```bash
echo "sdk.dir=$HOME/Library/Android/sdk" > local.properties   # macOS
./gradlew assembleDebug
```

Abre direto na `CatsActivity` com a lista paginada. Requer conexão com a internet.
