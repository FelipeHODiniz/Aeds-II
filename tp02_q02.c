#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_STR 200
#define MAX_LIST 30
#define DELIM ","

typedef struct {
    char show_id[MAX_STR];
    char type[MAX_STR];
    char title[MAX_STR];
    char director[MAX_STR];
    char cast[MAX_LIST][MAX_STR];
    int cast_len;
    char country[MAX_STR];
    char date_added[MAX_STR];
    int release_year;
    char rating[MAX_STR];
    char duration[MAX_STR];
    char listed_in[MAX_LIST][MAX_STR];
    int listed_len;
} Show;

void trim(char *str) {
    while (*str == ' ') str++;
    char *end = str + strlen(str) - 1;
    while (end > str && (*end == ' ' || *end == '\n')) *end-- = '\0';
}

void inicializarShow(Show *s) {
    strcpy(s->show_id, "NaN");
    strcpy(s->type, "NaN");
    strcpy(s->title, "NaN");
    strcpy(s->director, "NaN");
    strcpy(s->country, "NaN");
    strcpy(s->date_added, "NaN");
    strcpy(s->rating, "NaN");
    strcpy(s->duration, "NaN");
    s->release_year = 0;
    strcpy(s->cast[0], "NaN");
    s->cast_len = 1;
    strcpy(s->listed_in[0], "NaN");
    s->listed_len = 1;
}

void imprimirShow(Show s) {
    printf("=> %s ## %s ## %s ## %s ## [", s.show_id, s.title, s.type, s.director);
    for (int i = 0; i < s.cast_len; i++) {
        printf("%s%s", s.cast[i], (i < s.cast_len - 1 ? ", " : ""));
    }
    printf("] ## %s ## %s ## %d ## %s ## %s ## [", s.country, s.date_added, s.release_year, s.rating, s.duration);
    for (int i = 0; i < s.listed_len; i++) {
        printf("%s%s", s.listed_in[i], (i < s.listed_len - 1 ? ", " : ""));
    }
    printf("] ##\n");
}

void splitList(char *str, char list[][MAX_STR], int *len) {
    *len = 0;
    char *token = strtok(str, ",");
    while (token && *len < MAX_LIST) {
        trim(token);
        strcpy(list[(*len)++], token);
        token = strtok(NULL, ",");
    }
}

void lerShow(Show *s, char *linha) {
    inicializarShow(s);

    char *campos[11];
    int i = 0;
    bool aspas = false;
    char *ptr = strtok(linha, "");
    char *campo = malloc(MAX_STR);
    int campo_idx = 0;

    for (int j = 0; linha[j] != '\0'; j++) {
        char c = linha[j];
        if (c == '\"') aspas = !aspas;
        else if (c == ',' && !aspas) {
            campo[campo_idx] = '\0';
            campos[i++] = strdup(campo);
            campo_idx = 0;
        } else {
            campo[campo_idx++] = c;
        }
    }

    campo[campo_idx] = '\0';
    campos[i++] = strdup(campo);
    free(campo);

    strcpy(s->show_id, campos[0] && strlen(campos[0]) ? campos[0] : "NaN");
    strcpy(s->type, campos[1] && strlen(campos[1]) ? campos[1] : "NaN");
    strcpy(s->title, campos[2] && strlen(campos[2]) ? campos[2] : "NaN");
    strcpy(s->director, campos[3] && strlen(campos[3]) ? campos[3] : "NaN");

    if (campos[4] && strlen(campos[4])) {
        splitList(campos[4], s->cast, &s->cast_len);
    }

    strcpy(s->country, campos[5] && strlen(campos[5]) ? campos[5] : "NaN");
    strcpy(s->date_added, campos[6] && strlen(campos[6]) ? campos[6] : "NaN");
    s->release_year = (campos[7] && strlen(campos[7])) ? atoi(campos[7]) : 0;
    strcpy(s->rating, campos[8] && strlen(campos[8]) ? campos[8] : "NaN");
    strcpy(s->duration, campos[9] && strlen(campos[9]) ? campos[9] : "NaN");

    if (campos[10] && strlen(campos[10])) {
        splitList(campos[10], s->listed_in, &s->listed_len);
    }

    for (int k = 0; k < i; k++) free(campos[k]);
}

int main() {
    FILE *fp = fopen("/tmp/disneyplus.csv", "r");
    if (!fp) {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    char linha[1000];
    fgets(linha, sizeof(linha), fp); // cabeçalho

    char *mapa[5000];
    char *dados[5000];
    int mapa_len = 0;

    while (fgets(linha, sizeof(linha), fp)) {
        char *copy = strdup(linha);
        char *id = strtok(copy, ",");
        if (id) {
            mapa[mapa_len] = strdup(id);
            dados[mapa_len] = strdup(linha);
            mapa_len++;
        }
        free(copy);
    }

    fclose(fp);

    char entrada[MAX_STR];
    while (1) {
        fgets(entrada, MAX_STR, stdin);
        entrada[strcspn(entrada, "\n")] = 0;
        if (strcmp(entrada, "FIM") == 0) break;

        for (int i = 0; i < mapa_len; i++) {
            if (strcmp(entrada, mapa[i]) == 0) {
                Show s;
                lerShow(&s, dados[i]);
                imprimirShow(s);
                break;
            }
        }
    }

    for (int i = 0; i < mapa_len; i++) {
        free(mapa[i]);
        free(dados[i]);
    }

    return 0;
}
