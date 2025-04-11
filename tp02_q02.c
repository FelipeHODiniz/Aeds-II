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

int compararStrings(const void *a, const void *b) {
    const char *sa = (const char *)a;
    const char *sb = (const char *)b;
    return strcmp(sa, sb);
}


void splitList(char *str, char list[][MAX_STR], int *len) {
    *len = 0;
    char *token = strtok(str, ",");

    while (token && *len < MAX_LIST) {
        while (*token == ' ') token++;  // apenas tira espaços à esquerda

        strncpy(list[*len], token, MAX_STR - 1);
        list[*len][MAX_STR - 1] = '\0';
        (*len)++;

        token = strtok(NULL, ",");
    }

    qsort(list, *len, MAX_STR, compararStrings);
}


void lerShow(Show *s, char *linha) {
    inicializarShow(s);

    char *campos[20];
    int campoIndex = 0;

    char *token = malloc(strlen(linha) + 1);
    int i = 0, j = 0;
    bool aspas = false;

    while (linha[i]) {
        if (linha[i] == '\"') {
            aspas = !aspas;
        } else if (linha[i] == ',' && !aspas) {
            token[j] = '\0';
            campos[campoIndex++] = strdup(token);
            j = 0;
        } else {
            token[j++] = linha[i];
        }
        i++;
    }

    token[j] = '\0';
    campos[campoIndex++] = strdup(token);
    free(token);

    #define GET_CAMPO(i) ((i < campoIndex && strlen(campos[i]) > 0) ? campos[i] : "NaN")

    strncpy(s->show_id, GET_CAMPO(0), MAX_STR - 1);
    strncpy(s->type, GET_CAMPO(1), MAX_STR - 1);
    strncpy(s->title, GET_CAMPO(2), MAX_STR - 1);
    strncpy(s->director, GET_CAMPO(3), MAX_STR - 1);

    if (strcmp(GET_CAMPO(4), "NaN") != 0) {
        char castBuffer[500];
        strncpy(castBuffer, campos[4], sizeof(castBuffer) - 1);
        splitList(castBuffer, s->cast, &s->cast_len);
    }

    strncpy(s->country, GET_CAMPO(5), MAX_STR - 1);
    strncpy(s->date_added, GET_CAMPO(6), MAX_STR - 1);
    s->release_year = (strcmp(GET_CAMPO(7), "NaN") != 0) ? atoi(GET_CAMPO(7)) : 0;
    strncpy(s->rating, GET_CAMPO(8), MAX_STR - 1);
    strncpy(s->duration, GET_CAMPO(9), MAX_STR - 1);

    if (strcmp(GET_CAMPO(10), "NaN") != 0) {
        char listBuffer[500];
        strncpy(listBuffer, campos[10], sizeof(listBuffer) - 1);
        splitList(listBuffer, s->listed_in, &s->listed_len);
    }

    for (int k = 0; k < campoIndex; k++) free(campos[k]);
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
