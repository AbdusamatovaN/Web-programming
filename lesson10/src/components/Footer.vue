<template>
    <footer>
      <div>users: {{ cntU }} & posts: {{ cntP }}</div>
      <a href="/">Codeforces</a> &copy; 2021 by Mike Mirzayanov
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["cntU", "cntP"]
}
</script>

<style scoped>

</style>