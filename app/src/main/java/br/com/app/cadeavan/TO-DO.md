## TO-DO.md

### **Tarefas Concluídas**

1. **Location Repository:**
    - Criada a interface `LocationRepository`.
    - Implementada a classe `FirebaseLocationRepository` para lidar com operações específicas do Firebase.
    - O `LocationViewModel` foi atualizado para usar o `LocationRepository` injetado via Hilt.

2. **Dependency Injection:**
    - Configurado o Hilt no projeto.
    - O `LocationViewModel` e o `LocationRepository` estão sendo injetados corretamente.

3. **Error Handling:**
    - Implementada a classe `LocationError` para representar diferentes tipos de erros.
    - O `LocationViewModel` foi atualizado para usar `LocationError` em vez de `String` para mensagens de erro.
    - As telas `DriverScreen` e `TrackerScreen` foram atualizadas para exibir mensagens de erro específicas com base no tipo de erro.

4. **Location Updates:**
    - Implementado o `LocationService` para enviar atualizações de localização em tempo real para o Firebase.

5. **Firebase Realtime Database:**
    - Configurada a estrutura básica do Firebase, com dados de localização sendo armazenados no caminho `drivers/{driverId}`.

6. **Code Style and Readability:**
    - O código foi organizado e padronizado seguindo as convenções do Kotlin.

---

### **Tarefas em Andamento**

1. **Filtrar Atualizações de Localização:**
    - Implementar filtros para evitar o envio de localizações redundantes (por distância ou tempo).

2. **Melhorar a Estrutura de Dados no Firebase:**
    - Armazenar mais informações sobre a localização, como precisão, velocidade e timestamp.
    - Organizar os dados em um caminho mais estruturado, como `drivers/{driverId}/locations/{locationId}`.

---

### **Próximos Passos**

1. **Filtrar Atualizações de Localização:**
    - **O que fazer:**
        - Implementar filtros de distância (por exemplo, enviar apenas se o motorista se mover mais de 10 metros).
        - Implementar filtros de tempo (por exemplo, enviar apenas a cada 30 segundos).
    - **Por que fazer:**
        - Reduzir o consumo de bateria e dados.
        - Diminuir os custos do Firebase.
    - **Como fazer:**
        - Adicionar lógica no `LocationService` para verificar a distância e o tempo desde a última atualização.

2. **Melhorar a Estrutura de Dados no Firebase:**
    - **O que fazer:**
        - Armazenar mais informações sobre a localização, como precisão, velocidade e timestamp.
        - Organizar os dados em um caminho mais estruturado, como `drivers/{driverId}/locations/{locationId}`.
    - **Por que fazer:**
        - Facilitar consultas e análises no futuro.
        - Melhorar a escalabilidade do projeto.
    - **Como fazer:**
        - Atualizar o método `sendLocationToFirebase` no `FirebaseLocationRepository` para incluir mais campos.

3. **Testes Unitários:**
    - **O que fazer:**
        - Escrever testes unitários para o `LocationViewModel`, `LocationRepository` e `LocationService`.
    - **Por que fazer:**
        - Garantir que o código funcione conforme o esperado.
        - Facilitar a manutenção e a detecção de bugs.
    - **Como fazer:**
        - Usar bibliotecas como **JUnit**, **MockK** e **Turbine** para testar fluxos e comportamentos.

4. **Melhorar a UI:**
    - **O que fazer:**
        - Adicionar um ícone personalizado para a notificação do `LocationService`.
        - Melhorar a exibição da localização no mapa, com marcadores personalizados e animações.
    - **Por que fazer:**
        - Melhorar a experiência do usuário.
        - Tornar a interface mais atraente e funcional.

5. **Documentação e Organização:**
    - **O que fazer:**
        - Atualizar o `TO-DO.md` com as tarefas concluídas e as próximas etapas.
        - Documentar o código com comentários claros e explicativos.
    - **Por que fazer:**
        - Facilitar a colaboração e a manutenção do projeto.
        - Manter o progresso do projeto organizado e transparente.

---

### **Resumo**

- **Concluído:**
    - Implementação do `LocationRepository` e `FirebaseLocationRepository`.
    - Configuração do Hilt para injeção de dependências.
    - Implementação básica do `LocationService` e `LocationViewModel`.
    - Tratamento de erros com a classe `LocationError`.

- **Em Andamento:**
    - Filtragem de atualizações de localização.
    - Melhoria na estrutura de dados do Firebase.

- **Próximos Passos:**
    - Implementar filtros para atualizações de localização.
    - Melhorar a estrutura de dados no Firebase.
    - Escrever testes unitários.
    - Melhorar a UI e a documentação.