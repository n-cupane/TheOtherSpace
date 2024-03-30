# TheOtherSpace

# Lato Utente:

## Feature da implementare:

- [x]  Registrazione
- [x]  Login
- [x]  Visualizzazione spettacoli
- [x]  Acquisto biglietto
- [x]  Pagina informazioni personali con modifica
- [x]  Visualizzazione e gestione prenotazioni
- [x]  Recensione film
- [x]  Aggiunta film ai preferiti

# Lato Admin:

## Feature da implementare:

- [x]  CRUD film
- [x]  CRUD utenti
- [x]  CRUD proiezioni
- [x]  CRUD sale
- [ ]  Moderazione recensioni



# Da fixare:

- [x] Caratteri accentati nella descrizione dei film
- [x] Utilizzare session in tutte le servlet per controllare se l'utente è loggato
- [ ] Non mostrare proiezioni passate all'utente
- [x] Permettere solo a utenti loggati di arrivare alla scelta del posto
- [x] L'accesso come admin deve rimandare al pannello di controllo
- [ ] Impostare vincoli not null e unique nel DB tramite JPA
- [ ] Creare servlet di logout e gestire tutti i logout reindirizzandoli a questa
- [x] Aggiungere link a home tramite logo in tutte le servlet
- [ ] Valutare, come suggerito da Filippo, di non eliminare del tutto gli utenti dal DB ma di cambiarne solo la visibilità (forse anche altro come screening)
- [ ] Nell'aggiunta e nella modifica di una proiezione controllare che lo slot orario scelto per quella sala sia libero (query da aggiungere) --Niccolò
