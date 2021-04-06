from django.urls import path

from . import views


urlpatterns = [
    path('rank', views.rank, name='index'),
    path('store/<id>', views.store, name='index'),
    path('recommendation/<id>', views.recommendation, name='index'),
    path('review', views.reviewInsert, name='index'),
]